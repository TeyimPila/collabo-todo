package collabo.todo


class UserInterceptor {

    UserInterceptor() {
        match(controller: 'user', action: '*')
    }

    boolean before() {

        def currentAction = actionName

        if (currentAction == 'edit' ||
                currentAction == 'update' ||
                currentAction == 'delete') {

            String userId = session?.user?.id
            String paramsUserId = params?.id

            if (userId != paramsUserId) {
                flash.message = "You can only ${currentAction} yourself"
                redirect action: 'index'
                return false
            }
        }

        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
