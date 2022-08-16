package knight.arkham.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import static knight.arkham.game.helpers.Constants.PIXELS_PER_METER;

//De esta forma indico que esta clase hereda de GameObject
public class Player extends GameObject{

    private int jumpCounter;

    public Player(Body body, float width, float height) {
        super(body, width, height);

//        Debido a la herencia puedo acceder a todas las variables de gameObject, dentro de cada uno de mis metodos
        speed = 10;
        jumpCounter = 0;
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && jumpCounter <2){

//            Para obtener la fuerza ideal de nuestro salto, debemos multiplicara la masa de nuestro objeto
//            por un valor que adapte a nuestra necesidades, probar distintos si es necesario
            float jumpForce = body.getMass() * 18;

//            Antes de aplicar el impulso linear debemos setear nuestra velocidad lineal en y a 0
            body.setLinearVelocity(body.getLinearVelocity().x, 0);

//            Con este metodo le aplicamos un impulso a nuestro objeto ya en x o y
//            Debemos pasarle de segundo parametro la posicion actual de nuestro objeto
            body.applyLinearImpulse(new Vector2(0, jumpForce), body.getPosition(), true);
        }

//        Si la linear velocity en y es 0 esto quiere decir que ya dejamos de saltar y tocamos el suelo
        if (body.getLinearVelocity().y == 0){

            jumpCounter = 0;
        }


        body.setLinearVelocity(velocityX * speed, body.getLinearVelocity().y < 25 ? body.getLinearVelocity().y : 25);
    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
