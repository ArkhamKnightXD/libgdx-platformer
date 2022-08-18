package knight.arkham.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

//Clase abstracta de la que heredaran mis demas objetos, la posicion, speed, velocity, etc.., como es algo que
//Todos mis objetos tienen en comun no hay necesidad de repetirlo en cada uno de mis objetos
public abstract class GameObject {

    protected Body body;
    protected float positionX;
    protected float positionY;

    protected float speed;

    protected float velocityX;
    protected float velocityY;

    protected float width;
    protected float height;


    public GameObject(Body body, float width, float height) {

        positionX = body.getPosition().x;
        positionY = body.getPosition().y;
        velocityX = 0;
        velocityY = 0;
        speed = 0;

        this.body = body;
        this.width = width;
        this.height = height;
    }


//    Definimos los metodos update y render, los hacemos abstracto, para que toda clase que implemente gameObject
//    Tenga que tener estos elementos de forma obligatoria
    public abstract void update();

    public abstract void render(SpriteBatch batch);

    public Body getBody() {return body;}

    public float getPositionX() {return positionX;}

    public float getPositionY() {return positionY;}

    public float getWidth() {return width;}

    public float getHeight() {return height;}
}
