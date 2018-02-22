package collabo.todo

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User> {

    def setup() {
    }

    def cleanup() {
    }

    void "Test CRUD operations on the User domain"() {
        setup:
            def user = new User(
                    userName: 'testUser',
                    firstName: "John",
                    lastName: "Doe",
                    password: "pass",
                    email: "j.d@doe.com")

            user.save()
            def userId = user.id

        when:
            user.password = 'A new password'
            user = User.get(userId)

        then:
            "A new password" == user.password
            "John" == user.firstName

        when:
            user.delete()

        then:
            null == User.get(userId)
    }
}
