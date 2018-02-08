package collabo.todo

class Todo {

    static searchable = true

    String name
    String note
    Date createdDate
    Date dueDate
    Date completedDate
    String priority
    String status
    byte[] associatedFile
    String fileName
    String contentType

    User owner
    Category category

    /**
     * Defines Relationships
     */
    static belongsTo = [User, Category]


    static constraints = {
        name(blank: false)
        createdDate()
        priority()
        status()
        note(maxSize: 1000, nullable: true)
        completedDate(nullable: true)
        dueDate(nullable: true)
        associatedFile(nullable:true)
    }

    String toString() {
        name
    }
}
