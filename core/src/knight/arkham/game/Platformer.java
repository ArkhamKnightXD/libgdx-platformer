package knight.arkham.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import knight.arkham.game.screens.GameScreen;

public class Platformer extends Game {

	public static Platformer INSTANCE;

	private int widthScreen;

	private int heightScreen;

	private OrthographicCamera camera;


	public Platformer() {

		INSTANCE = this;
	}


	@Override
	public void create () {

		//Aqui obtenemos el ancho y alto de la pantalla que definimos en el desktoplauncher
		widthScreen = Gdx.graphics.getWidth();
		heightScreen = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, widthScreen, heightScreen);

		setScreen(new GameScreen(camera));
	}


	@Override
	public void render () {

	}


	@Override
	public void dispose () {

	}
}
