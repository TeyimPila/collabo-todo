package collabo.todo

class User {

    String firstName
    String lastName
    String userName
    String password
    String email
    boolean active = true
    String confirmPassword

    static transients = ["confirmPassword"]

    static hasMany = [address: Address, buddyLists: BuddyList, todos: Todo, categories: Category, authorities: Authority]
    static belongsTo = Authority

//    static hasOne = Address

    static constraints = {
        userName(blank: false, unique: true)
        password(blank: false, minLength: 3)
        email(email: true)
        firstName(blank: false)
        lastName(blank: false)
        address(nullable: true)
        active()
    }


    String toString() {
        return "$lastName, $firstName"
    }

    String dump() {
        return "FirstName: ${firstName}, LastName: ${lastName}, UserName: ${userName}, Password: ${password}, Email: ${email}"
    }


}

