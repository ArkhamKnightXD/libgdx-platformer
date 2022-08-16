package knight.arkham.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import knight.arkham.game.screens.GameScreen;

public class Platformer extends Game {

	public static Platformer INSTANCE;

	public Platformer() {

		INSTANCE = this;
	}

//Lo unico que debemos definir en nuestra clase principal es la camara global, aqui no debemos definir ninguna
//	propiedad a la cual se le deba hacer dispose, como el spritebatch o font
	@Override
	public void create () {

		int widthScreen = Gdx.graphics.getWidth();
		int heightScreen = Gdx.graphics.getHeight();

		OrthographicCamera globalCamera = new OrthographicCamera();
		globalCamera.setToOrtho(false, widthScreen, heightScreen);

		setScreen(new GameScreen(globalCamera));
	}


	//Esta linea de codigo es jodidamente importante, sin esta no puedo renderizar nada en las demas pantallas
	//me causo demasiados problemas
	public void render() {
		super.render(); // important!
	}


	@Override
	public void dispose () {

	}
}
