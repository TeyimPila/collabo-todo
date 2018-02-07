package collabo.todo

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CategoryController {

    CategoryService categoryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        def user = User.get(session.user.id)

        params.max = Math.min(max ?: 10, 100)

        respond Category.findAllByUser(user, params), model: [categoryCount: categoryService.count()]
    }

    def show(Long id) {
        respond categoryService.get(id)
    }

    def create() {
        respond new Category(params)
    }

    def save(Category category) {
        if (category == null) {
            notFound()
            return
        }

        category.user = session.user

        try {
            categoryService.save(category)
        } catch (ValidationException e) {
            respond category.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'category.label', default: 'Category'), category.id])
                redirect category
            }
            '*' { respond category, [status: CREATED] }
        }
    }

    def edit(Long id) {
        if (session.user.id != Category.get(id).user.id) {
            flash.message = "You can only edit your own categories"
            respond category.errors, view: 'edit'
            return
        }
        respond categoryService.get(id)
    }

    def update(Category category) {
        if (category == null) {
            notFound()
            return
        }

        if (session.user.id != category.user.id) {
            flash.message = "You can only update your own categories"
            respond category.errors, view: 'edit'
            return
        }

        try {
            category.user = session.user
            categoryService.save(category)
        } catch (ValidationException e) {
            respond category.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'category.label', default: 'Category'), category.id])
                redirect category
            }
            '*' { respond category, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        if (session.user.id != Category.get(id).user.id) {
            flash.message = "You can only delete your own categories"
            respond category.errors, view: 'edit'
            return
        }

        categoryService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'category.label', default: 'Category'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'category.label', default: 'Category'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
