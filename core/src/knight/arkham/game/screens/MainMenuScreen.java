package knight.arkham.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.game.Platformer;

//probare con esta clase a ver si renderiza algo
public class MainMenuScreen extends ScreenAdapter {

    private final Platformer game = Platformer.INSTANCE;

    private final OrthographicCamera camera;

    public MainMenuScreen() {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 640);
    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();


        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);

        game.batch.end();

        if (Gdx.input.isTouched()) {

            game.setScreen(new GameScreen(camera));
            dispose();
        }

    }


    @Override
    public void dispose() {

    }
}
