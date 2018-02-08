package collabo.todo

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
