package collabo.todo

import grails.testing.mixin.integration.Integration
import grails.transaction.*


class UserControllerTestSpec extends GroovyTestCase {

    User user
    UserController controller


    void setUp() {
        user = new User(userName: 'Pila', firstName: 'Teyim', lastName: 'Pila')
        user.save()


        controller = new UserController()
    }

    void tearDown() {
        user.delete()
    }

    void testHandleLogin() {
        controller.params.userName = user.userName

        controller.handleLogin()

        def sessionUser = controller.session.user

        assert sessionUser

        assertEquals('Expected Ids to match', user.id, sessionUser.id)

        assertEquals("/todo", controller.response.redirecturl)
    }

    /**
     * Test the UserController.handleLogin action.
     *
     * If the login fails, it will redirect to login and set a flash message.
     */

    void testHandleLoginInvalidUser() {
        // Setup controller parameters
        controller.params.userName = "INVALID_USER_NAME"
        // Call the action
        controller.handleLogin()
        assertEquals "/user/login", uc.response.redirectedUrl
        def message = uc.flash.message
        assert message
        assert message.startsWith("User not found")
    }

    /**
     * Test the UserController.login action
     *
     * If the logout action succeeds, it will remove the user object from
     * the session.
     */
    void testLogout() {
        // make it look like user is logged in
        controller.session.user = user
        controller.logout()
        def sessUser = controller.session.user
        assertNull("Expected session user to be null", sessUser)
        assertEquals "/user/login", controller.response.redirectedUrl
    }

    void "test something"() {
        expect: "fix me"
        true == false
    }
}
