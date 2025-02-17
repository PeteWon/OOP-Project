// package io.github.some_example_name.lwjgl3.IO.Input;

// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.Input;

// public class Keyboard {
//     private int left, right, up, down, esc, space;
//     private int speed;

//     public Keyboard() {

//         this.left = Input.Keys.LEFT;
//         this.right = Input.Keys.RIGHT;
//         this.up = Input.Keys.UP;
//         this.down = Input.Keys.DOWN;
//         this.esc = Input.Keys.ESCAPE;
//         this.space = Input.Keys.SPACE;
//         this.speed = 5;
//     }

//     public float moveLeftRight(float x) {

//         if (Gdx.input.isKeyPressed(left)) {
//             return x - speed;

//         }
//         if (Gdx.input.isKeyPressed(right)) {
//             return x + speed;
//         }
//         return x;
//     }

//     public float moveUpDown(float y) {

//         if (Gdx.input.isKeyPressed(up)) {
//             return y + speed;
//         }
//         if (Gdx.input.isKeyPressed(down)) {
//             return y - speed;
//         }

//         return y;
//     }
// }

package io.github.some_example_name.lwjgl3.IO.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Keyboard {
    public static float getHorizontal() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            return -1;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            return 1;
        return 0;
    }

    public static float getVertical() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            return 1;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            return -1;
        return 0;
    }
}
