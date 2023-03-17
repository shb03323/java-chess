package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(color, 'r');
    }

    @Override
    public Direction findDirection(Square current, Square destination) {
        int fileDifference = current.getFileDifference(destination);
        int rankDifference = current.getRankDifference(destination);
        return PieceDirection.STRAIGHT.findDirection(fileDifference, rankDifference);
    }
}
