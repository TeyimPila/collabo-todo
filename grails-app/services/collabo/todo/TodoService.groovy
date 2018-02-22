package collabo.todo

import grails.gorm.services.Service

@Service(Todo)
interface TodoService {

    boolean transactional = true

    Todo get(Serializable id)

    List<Todo> list(Map args)

    Long count()

    void delete(Serializable id)

    Todo save(Todo todo)

}