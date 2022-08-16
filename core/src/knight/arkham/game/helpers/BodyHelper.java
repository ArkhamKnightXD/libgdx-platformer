package knight.arkham.game.helpers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class BodyHelper {

    public static void createBody(Box2DBody box2DBody){

        BodyDef bodyDefinition = new BodyDef();

        bodyDefinition.type = box2DBody.isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;

        bodyDefinition.type =  BodyDef.BodyType.StaticBody;

        Body body = box2DBody.world.createBody(bodyDefinition);

        body.createFixture(box2DBody.polygonShape, 1000);
    }
}
