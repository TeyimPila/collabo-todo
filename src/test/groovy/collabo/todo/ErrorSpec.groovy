package collabo.todo

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ErrorSpec extends Specification implements DomainUnitTest<Error> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
