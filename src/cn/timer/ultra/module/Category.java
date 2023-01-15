package cn.timer.ultra.module;

public enum Category {
    /*Combat,
    Movement,*/
    Render(0,"Render"),
    Player(1,"Player");
    public final String location;
    //World
    final int index;
    public int selectedIndex;
    public float selectedTrans;
    public float lastSelectedTrans;
    public float seenTrans;

    Category(int index, String name) {
        this.index = index;
        this.location = name;
    }

    public int getIndex() {
        return index;
    }

    public static Category get(int index) {
        for (Category value : Category.values()) {
            if (value.getIndex() == index)
                return value;
        }
        return null;
    }
}
