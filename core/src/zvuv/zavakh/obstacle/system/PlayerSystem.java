package zvuv.zavakh.obstacle.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import zvuv.zavakh.obstacle.common.Mappers;
import zvuv.zavakh.obstacle.component.MovementComponent;
import zvuv.zavakh.obstacle.component.PlayerComponent;
import zvuv.zavakh.obstacle.config.GameConfig;

public class PlayerSystem extends IteratingSystem {

    private static final Family playerFamily = Family.all(
            PlayerComponent.class,
            MovementComponent.class
    ).get();

    public PlayerSystem() {
        super(playerFamily);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MovementComponent movementComponent = Mappers.MOVEMENT_MAPPER.get(entity);

        movementComponent.setxSpeed(0f);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            movementComponent.setxSpeed(GameConfig.MAX_PLAYER_X_SPEED);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            movementComponent.setxSpeed(-GameConfig.MAX_PLAYER_X_SPEED);
        }
    }
}
