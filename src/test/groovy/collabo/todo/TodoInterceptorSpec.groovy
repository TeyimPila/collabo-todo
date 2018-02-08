package collabo.todo

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class TodoInterceptorSpec extends Specification implements InterceptorUnitTest<TodoInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test todo interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"todo")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
