package gwttutorial

class Author {

    String firstName
    String lastName

    static hasMany = [books: Book]

    static constraints = {
        books(nullable: true)
    }
}
