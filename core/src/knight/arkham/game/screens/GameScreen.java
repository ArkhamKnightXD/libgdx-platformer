package knight.arkham.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.game.Platformer;
import knight.arkham.game.helpers.TileMapHelper;
import knight.arkham.game.objects.Player;

import static knight.arkham.game.helpers.Constants.PIXELS_PER_METER;

public class GameScreen extends ScreenAdapter {

    private final Platformer game;

    private final SpriteBatch batch;

    private final OrthographicCamera camera;

    //Esta variable se utiliza para almacenar todos nuestro objetos box2d
    private final World world;

    //debug purpose?
    private final Box2DDebugRenderer box2DDebugRenderer;

    private final OrthogonalTiledMapRenderer mapRenderer;

    private Player player;


    public GameScreen(OrthographicCamera globalCamera) {

        game =  Platformer.INSTANCE;

        //En la mayoria de casos utilizamos this para diferencia la variable de la clase de la
        //que llegara en el contructor o en el setter, asi que siempre y cuando tengamos nombres diferentes
        //entre las variable locales y las que llegan el this no es necesario
        camera = globalCamera;

        batch = new SpriteBatch();


        //para instanciar nuestro objeto world debemos pasarle un vector2 en el que especificamos la gravedad de X y Y
        //Seteare la gravedad en con el valor de 25 negativo, como es gravedad debe de ser negativo pues va para abajo
//Para que mi objeto no sea tan liviano seteare la gravedad en 25 en vez de 9.88
        world = new World(new Vector2(0,-25), false);

        box2DDebugRenderer = new Box2DDebugRenderer();

        TileMapHelper tileMapHelper = new TileMapHelper(this);

        mapRenderer = tileMapHelper.setupMap();
    }

    @Override
    public void show() {

    }

    //Creare un metodo update igual que en unity donde manejare la actualizacion de los objetos y lo llamare en render
    private void update (){

        //el 1/60 es porque el juego corre a 60fps, jugare con los valores luego que no se para que sirven
        world.step(1/60f, 6, 2);

        cameraUpdate();

        player.update();

        //camera combined envia la Camera's view and projection matrices.
        batch.setProjectionMatrix(camera.combined);

        // indicamos como sera manejada la vista en el mapa, en este caso manejaremos las vista con la camara
        mapRenderer.setView(camera);

        //cerrara el juego cuando se presione la tecla escape
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){

            dispose();
            Gdx.app.exit();
        }
    }

    //metodo encargado de hacer que la camara siga a nuestro jugador
    private void cameraUpdate(){

        Vector3 cameraPosition = camera.position;

//        Hago la operaciones extra para conseguir un movimiento de camara mas smooth
        cameraPosition.x = Math.round(player.getBody().getPosition().x * PIXELS_PER_METER * 10) / 10f;
        cameraPosition.y = Math.round(player.getBody().getPosition().y * PIXELS_PER_METER * 10) / 10f;

        //Le indicamos una nueva posicion inicial a nuestra camara y luego actualizamos la camara con la nueva posicion
        camera.position.set(cameraPosition);
        camera.update();
    }


    @Override
    public void render(float delta) {

        update();

        ScreenUtils.clear(0, 0, 0, 1);

        //ponemos a renderizar el mapa antes los batch siempre hacerlo de esta forma
        mapRenderer.render();

        batch.begin();

        batch.end();


        box2DDebugRenderer.render(world, camera.combined.scl(PIXELS_PER_METER));
    }


    @Override
    public void hide() {

        dispose();
    }


    @Override
    public void dispose() {

        batch.dispose();
        world.dispose();
        mapRenderer.dispose();
        box2DDebugRenderer.dispose();
    }

    public World getWorld() {return world;}

    public void setPlayer(Player player) {this.player = player;}
}
