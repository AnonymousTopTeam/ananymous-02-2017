package application.mechanics.base;

import org.jetbrains.annotations.NotNull;

public class Ray {
    @NotNull
    private MyVector vector;
    @NotNull
    private Coordinates point;

    public Ray(@NotNull MyVector vector, @NotNull Coordinates point) {
        this.vector = vector;
        this.point = point;
    }

    @NotNull
    public MyVector getVector() {
        return vector;
    }

    public void setVector(@NotNull MyVector vector) {
        this.vector = vector;
    }

    @NotNull
    public Coordinates getPoint() {
        return point;
    }

    public void setPoint(@NotNull Coordinates point) {
        this.point = point;
    }
}
