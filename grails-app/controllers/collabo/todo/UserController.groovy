package collabo.todo

import grails.converters.JSON
import grails.converters.XML
import grails.validation.ValidationException
import org.springframework.validation.BindingResult

import static org.springframework.http.HttpStatus.*

class UserController {

    UserService userService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def users = userService.list(params)

        /**
         * This allows the request to specify the response type as
         * a query string parameter: http://localhost:8080/user/index?format=json
         */
        withFormat {
            html { respond users, model: [userCount: userService.count()] }
            json { respond users, model: [userCount: userService.count()] as JSON }
            xml { render users as XML }
        }
    }

    def show(Long id) {
        respond userService.get(id)
    }

    def create() {
        respond new User(params)
    }

    def save(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def edit(Long id) {

        def user = userService.get(id)

        if (!user) {
            notFound()
            return
        }

        respond userService.get(id)
    }

    def update(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def login() {
    }

    def register() {
    }

    def handleLogin() {
        def user = User.findByUserName(params.userName)
        if (!user) {
            flash.message = 'user.notfound.message'
            flash.args = [params.userName]
            flash.defaultMsg = 'User Not found'

            redirect(action: 'login')
            return
        }
        session.user = user

        redirect(controller: 'todo')
    }

    def logout = {
        if (session.user) {
            session.user = null
            redirect(action: 'login')
        }
    }

    def handleRegistration = {
        def user = new User()
        log.info("HANDLE REGISTRATION")
        // Process the captcha request
        def captchaText = session.captcha
        session.captcha = null
        if (params.captcha.toUpperCase() == captchaText) {
            if (params.password != params.confirm) {
                flash.message = "The two passwords you entered don't match!"
                redirect(action: register)
            } else {
                log.info "before save"
                // Let's hash the password
                user.properties = params as BindingResult
                println(user.dump())
                if (user.save()) {
                    log.info "saved redirecting to user controller"
                    // Let's log them in
                    session.user = user
                    redirect(controller: 'todo')
                } else {
                    log.info "didn't save"
                    flash.user = user
                    redirect(action: register)
                }
            }
        } else {
            log.info "Captcha Not Filled In"
            flash.message = "Access code did not match."
            redirect(controller: 'user')
        }
    }

}
