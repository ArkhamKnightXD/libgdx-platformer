package knight.arkham.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.game.helpers.TileMapHelper;
import static knight.arkham.game.helpers.Constants.PIXELSPERMETER;

public class GameScreen extends ScreenAdapter {

    private final OrthographicCamera camera;

    private final SpriteBatch batch;

    //Esta variable se utiliza para almacenar todos nuestro objetos box2d
    private final World world;

    //debug purpose?
    private final Box2DDebugRenderer box2DDebugRenderer;

    private final TileMapHelper tileMapHelper;

    private final OrthogonalTiledMapRenderer mapRenderer;

    private final Texture testImg;

    public GameScreen(OrthographicCamera globalCamera) {

        //En la mayoria de casos utilizamos this para diferencia la variable de la clase de la
        //que llegara en el contructor o en el setter, asi que siempre y cuando tengamos nombres diferentes
        //entre las variable locales y las que llegan el this no es necesario
        camera = globalCamera;

        batch = new SpriteBatch();

        testImg = new Texture("bucket.png");

        //para instanciar nuestro objeto world debemos pasarle un vector2 en el que especificamos la gravedad de X y Y
        //por ahora seran 0
        world = new World(new Vector2(0,0), false);

        box2DDebugRenderer = new Box2DDebugRenderer();

        tileMapHelper = new TileMapHelper();
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

        //camera combined envia la Camera's view and projection matrices.
        batch.setProjectionMatrix(camera.combined);

        // indicamos como sera manejada la vista en el mapa
        mapRenderer.setView(camera);

        //cerrara el juego cuando se presione la tecla escape
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
    }

    //metodo encargado de hacer que la camara siga a nuestro jugador
    private void cameraUpdate(){

        //Le indicamos una nueva posicion y luego actualizamos la camara
        camera.position.set(new Vector3(0,0,0));
        camera.update();
    }


    @Override
    public void render(float delta) {

        update();

        //limpiamos los colores de la pantalla utilizando metodos de opengl
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //ponemos a renderizar el mapa antes los batch siempre hacerlo de esta forma
        mapRenderer.render();

        batch.begin();
        //no se muestra nada en pantalla debo ver como resuelvo esto mirando el anterior proyecto
        batch.draw(testImg, 500, 400);
        batch.end();

        //se recomienda llamar el debugrenderer despues del batch.end
        //Aqui le paso el world y la camara combined e indico la escala de pixeles
        box2DDebugRenderer.render(world, camera.combined.scl(PIXELSPERMETER));
    }


    @Override
    public void hide() {

    }


    @Override
    public void dispose() {

        batch.dispose();
        world.dispose();
        mapRenderer.dispose();
        box2DDebugRenderer.dispose();
    }
}
