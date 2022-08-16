package knight.arkham.game.helpers;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import knight.arkham.game.objects.Player;
import knight.arkham.game.screens.GameScreen;

import static knight.arkham.game.helpers.Constants.PIXELS_PER_METER;

//clase encargada del manejo del tiledmap
public class TileMapHelper {

    private final GameScreen gameScreen;

    public TileMapHelper(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }


    public OrthogonalTiledMapRenderer setupMap() {

        TiledMap tiledMap;

        //cargo el mapa
        tiledMap = new TmxMapLoader().load("maps/test.tmx");

//        De esta forma obtengo la capa en la que estan los objetos a los que le agregue colisiones en el mapa
//        Pues tengo la capa background que son las imagenes y la capa collision donde manejo las colisiones
        MapObjects collisionObjects = tiledMap.getLayers().get("collisions").getObjects();

        parseMapObjectsToStaticBodies(collisionObjects);

        //retorno el renderizador de mapa que sera utilizado para ser mostrado en pantalla
        return new OrthogonalTiledMapRenderer(tiledMap);
    }

    private void parseMapObjectsToStaticBodies(MapObjects mapObjects) {

//        itero el listado de mapObject y cuando sea un rectangle object, que fue el elemento que
//        utilice para todas las colisiones de mis elementos
        for (MapObject mapObject : mapObjects) {

//            Cuando mi mapObject sea una instancia polygonShape creare un static body
            if (mapObject instanceof PolygonMapObject)
                createStaticBody(((PolygonMapObject) mapObject));

            if (mapObject instanceof RectangleMapObject) {

                Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();

                String rectangleName = mapObject.getName();

                if (rectangleName.equals("player")) {

                    Body playerBody = BodyHelper.createBody(
                            new Box2DBody(
                                    rectangle.getX() + rectangle.getWidth() / 2,
                                    rectangle.getY() + rectangle.getHeight() / 2,
                                    rectangle.getWidth(), rectangle.getHeight(),
                                    false, 1000, gameScreen.getWorld()
                            )
                    );

                    gameScreen.setPlayer(new Player(playerBody, rectangle.getWidth(), rectangle.getHeight()));
                }

            }

        }
    }


    private void createStaticBody(PolygonMapObject mapObject) {

        PolygonShape shape = createPolygonShape(mapObject);

        BodyHelper.createStaticBody(new Box2DBody(true, 1000, gameScreen.getWorld(), shape));

        shape.dispose();
    }

    private PolygonShape createPolygonShape(PolygonMapObject polygonMapObject) {

//        Obtengo los vertices totales de mi poligono actual, mediante este metodo, pues este me da los vertices
//        Luego de hacer un calculo nos da la posicion actual que los vertices de nuestro objeto
//        tendran en nuestro mundo (World)
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();

//        Aqui creo un arreglo de vectores para guardar las posiciones X y Y que tendran mis vertices en el mundo(World)
//        El vertices.length me da el doble de elementos que necesito por eso divido entre 2
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; i++) {

//            Guardo las coordenadas X y Y de mi vertice, para obtener estos datos es necesario multiplicar *2
//            No estoy consciente porque es necesario multiplicar * 2
            Vector2 current = new Vector2(vertices[i * 2] / PIXELS_PER_METER, vertices[i * 2 + 1] / PIXELS_PER_METER);

//            Y las guardo en el arreglo que contendra todas las posiciones en el mundo
            worldVertices[i] = current;
        }

        PolygonShape polygonShape = new PolygonShape();

//        Finalmente creo un polygonShape, con el arreglo de vertices, que al final contienen arreglo de vectores2
//        Que les indicaran al polygonShape las dimensiones que debe de tener
        polygonShape.set(worldVertices);

        return polygonShape;
    }
}
