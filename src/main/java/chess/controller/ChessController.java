package chess.controller;

import chess.domain.Board;
import chess.domain.square.Square;
import chess.dto.GameStatusDto;
import chess.dto.SquareDto;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final Board board = Board.create();

    public void run() {
        OutputView.printStartMessage();
        start();
        play();
        InputView.terminate();
    }

    private void start() {
        try {
            validateStartCommand(InputView.readCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            start();
        }
    }

    private void validateStartCommand(Command command) {
        if (command.equals(Command.START)) {
            return;
        }
        throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
    }

    // TODO: 개선 필요
    private void play() {
        OutputView.printGameStatus(GameStatusDto.from(board));
        while (InputView.readCommand() == Command.MOVE) {
            String current = InputView.readSquare();
            String destination = InputView.readSquare();
            try {
                move(current, destination);
                OutputView.printGameStatus(GameStatusDto.from(board));
            } catch (RuntimeException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void move(String current, String destination) {
        SquareDto currentDto = SquareDto.of(current);
        Square currentSquare = Square.of(currentDto.getFile(), currentDto.getRank());
        SquareDto destinationDto = SquareDto.of(destination);
        Square destinationSquare = Square.of(destinationDto.getFile(), destinationDto.getRank());
        board.move(currentSquare, destinationSquare);
    }
}
