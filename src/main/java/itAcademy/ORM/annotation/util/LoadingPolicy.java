package itAcademy.ORM.annotation.util;

public enum LoadingPolicy {

    /**
     * Loads target entity at loading of parent
     * entity. May cause huge overheads.
     */
    EAGER,

    /**
     * Does not load target entity at loading of
     * parent entity, leaves it as <code>null</code>
     * and defers its query to the developer.
     *
     * <p> Should be the preferred case
     * on most cardinalities where one side of the
     * statement is "many".
     */
    LAZY
}
