package collabo.todo

class Todo {

    static searchable = true

    String name
    String note
    Date createdDate
    Date startDate
    Date dueDate
    Date completedDate
    Date lastModifiedDate
    String priority
    String status
    byte[] associatedFile
    String fileName
    String contentType

    User ownedBy
    Category category

    /**
     * Defines Relationships
     */
    static belongsTo = [User, Category]
    static hasMany = [keywords: Keyword]


    static constraints = {
        ownedBy(nullable: false)
        name(blank: false)
        createdDate()
        lastModifiedDate()
        priority()
        status()
        note(maxSize: 1000, nullable: true)
        dueDate(nullable: true)
        associatedFile(nullable: true)
        completedDate(nullable: true,
                validator: { val, obj ->
                    if (val != null) {
                        return val.after(obj.createdDate)
                    }
                    return true
                })
        startDate(nullable: true,
                validator: {
                    if (it?.compareTo(new Date()) < 0) {
                        return false
                    }
                    return true
                })
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
