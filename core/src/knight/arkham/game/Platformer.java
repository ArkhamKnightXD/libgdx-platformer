package knight.arkham.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import knight.arkham.game.screens.GameScreen;
import knight.arkham.game.screens.MainMenuScreen;

public class Platformer extends Game {

	public static Platformer INSTANCE;

	private int widthScreen;

	private int heightScreen;

	private OrthographicCamera globalCamera;

	public SpriteBatch batch;

	public BitmapFont font;


	public Platformer() {

		INSTANCE = this;
	}


	@Override
	public void create () {

		//Aqui obtenemos el ancho y alto de la pantalla que definimos en el desktoplauncher
		widthScreen = Gdx.graphics.getWidth();
		heightScreen = Gdx.graphics.getHeight();

		globalCamera = new OrthographicCamera();
		globalCamera.setToOrtho(false, widthScreen, heightScreen);

		batch = new SpriteBatch();
		font = new BitmapFont();

		setScreen(new MainMenuScreen());
	}


	@Override
	public void render () {

	}


	@Override
	public void dispose () {

	}
}
