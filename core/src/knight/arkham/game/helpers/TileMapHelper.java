package knight.arkham.game.helpers;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

//clase encargada del manejo del tiledmap
public class TileMapHelper {

    public TileMapHelper() {
    }


    public OrthogonalTiledMapRenderer setupMap (){

        TiledMap tiledMap;

        //cargo el mapa
        tiledMap = new TmxMapLoader().load("maps/first-level.tmx");

        //retorno el renderizador de mapa que sera utilizado para ser mostrado en pantalla
        return new OrthogonalTiledMapRenderer(tiledMap);
    }
}
