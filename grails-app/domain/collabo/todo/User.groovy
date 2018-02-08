package collabo.todo

class User {

    String firstName
    String lastName
    String userName
    String password
    String email
    boolean active = true
    String confirmPassword

    Address address = new Address()


    static transients = ["confirmPassword"]

    static hasMany = [buddyLists: BuddyList, todos: Todo, categories: Category, authorities: Authority]
    static belongsTo = Authority

    static embedded = ['address']

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

class Address {

    String addressLine1
    String addressLine2
    String city
    String state
    String zipCode
    String country

    static constraints = {
        addressLine1(nullable: true)
        addressLine2(nullable: true)
        city(nullable: true)
        state(nullable: true)
        zipCode(nullable: true)
        country(nullable: true)
    }
}
