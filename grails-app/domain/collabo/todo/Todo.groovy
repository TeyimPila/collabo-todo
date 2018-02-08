package collabo.todo

class Todo {

    static searchable = true

    String name
    String note
    Date createdDate
    Date dueDate
    Date completedDate
    Date lastModifiedDate
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
    static hasMany = [keywords: Keyword]


    static constraints = {
        name(blank: false)
        createdDate()
        lastModifiedDate()
        priority()
        status()
        note(maxSize: 1000, nullable: true)
        completedDate(nullable: true)
        dueDate(nullable: true)
        associatedFile(nullable: true)
    }


    def beforeInsert = {
        createdDate = new Date()
        lastModifiedDate = new Date()
    }


    def beforeUpdate = {
        lastModifiedDate = new Date()
    }

    String toString() {
        name
    }
}
