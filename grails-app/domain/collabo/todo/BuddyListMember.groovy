package collabo.todo

class BuddyListMember {


    String nickName
    User user
    BuddyList buddyList

    static belongsTo = BuddyList

    static constraints = {
        nickName(blank: false)
        user(nullable: false)
    }

    String toString() {
        return "${nickName} - ${buddyList?.name} - ${user?.userName}"
    }
}
