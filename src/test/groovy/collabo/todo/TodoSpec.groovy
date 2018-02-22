package collabo.todo

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TodoSpec extends Specification implements DomainUnitTest<User> {

    def setup() {
    }

    def cleanup() {
    }


    void "Test the Todo domain custom validation"() {
        when:
        def todo = new Todo(
                owner: new User(),
                name: "Validation Test",
                note: "Detailed web app description",
                createdDate: new Date(),
                dueDate: new Date(),
                lastModifiedDate: new Date(),
                priority: '1',
                status: '1'
        )

        then:
        todo.validate()

        when:
        todo.completedDate = new Date() - 1
        todo.name = null

        then:
        !todo.validate()

        when:
        todo.completedDate = new Date() + 3

        then:
        todo.validate()
    }
}
