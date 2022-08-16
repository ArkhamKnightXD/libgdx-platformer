package knight.arkham.game.helpers;

import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DBody {

    public boolean isStatic;
    public float density;
    public World world;

    public PolygonShape polygonShape;


    public Box2DBody(boolean isStatic, float density, World world, PolygonShape polygonShape) {
        this.isStatic = isStatic;
        this.density = density;
        this.world = world;
        this.polygonShape = polygonShape;
    }
}
