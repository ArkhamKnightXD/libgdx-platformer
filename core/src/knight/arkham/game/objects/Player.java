package knight.arkham.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import static knight.arkham.game.helpers.Constants.PIXELS_PER_METER;

//De esta forma indico que esta clase hereda de GameObject
public class Player extends GameObject{

    public Player(Body body, float width, float height) {
        super(body, width, height);

//        Debido a la herencia puedo acceder a todas las variables de gameObject, dentro de cada uno de mis metodos
        speed = 4f;
    }

    @Override
    public void update() {

        positionX = body.getPosition().x * PIXELS_PER_METER;
        positionY = body.getPosition().y * PIXELS_PER_METER;

        playerMovement();
    }

    private void playerMovement() {

        velocityX = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.D))
            velocityX = 1;

        if (Gdx.input.isKeyPressed(Input.Keys.A))
            velocityX = -1;

        body.setLinearVelocity(velocityX * speed, 0);
    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
