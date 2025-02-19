package io.github.some_example_name.lwjgl3.application;

public class Tree extends StaticObject {
    public Tree(float x, float y) {
        super(x, y, 50, 50, "tree.png"); // ✅ Tree has a default size and texture
    }
}
