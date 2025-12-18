package org.kniit.patterns;

public class StateExample {

    public static void main(String[] args) {
        PlayerContext player = new PlayerContext();

        player.play();
        player.pause();
        player.play();
        player.stop();
        player.pause();
    }
}

interface PlayerState {
    void play(PlayerContext ctx);
    void pause(PlayerContext ctx);
    void stop(PlayerContext ctx);

}

class PlayerContext {

    private PlayerState state;

    public PlayerContext() {
        this.state = new StoppedState();
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public void play() {state.play(this);}

    public void pause() {state.pause(this);}

    public void stop() {state.stop(this);}

}

class StoppedState implements PlayerState {

    @Override
    public void play(PlayerContext ctx) {
        System.out.println("Старт воспроизведения");
        ctx.setState(new PlayingState());
    }

    @Override
    public void pause(PlayerContext ctx) {
        System.out.println("Нельзя поставить на паузу — плеер остановлен");
    }

    @Override
    public void stop(PlayerContext ctx) {
        System.out.println("Уже остановлен");
    }

}

class PlayingState implements PlayerState {

    @Override
    public void play(PlayerContext ctx) {
        System.out.println("Уже играет");
    }

    @Override
    public void pause(PlayerContext ctx) {
        System.out.println("Пауза");
        ctx.setState(new PausedState());
    }

    @Override
    public void stop(PlayerContext ctx) {
        System.out.println("Стоп воспроизведения");
        ctx.setState(new StoppedState());
    }

}

class PausedState implements PlayerState {

    @Override
    public void play(PlayerContext ctx) {
        System.out.println("Продолжить с паузы");
        ctx.setState(new PlayingState());
    }

    @Override
    public void pause(PlayerContext ctx) {
        System.out.println("Уже на паузе");
    }

    @Override
    public void stop(PlayerContext ctx) {
        System.out.println("Стоп с паузы");
        ctx.setState(new StoppedState());
    }

}

