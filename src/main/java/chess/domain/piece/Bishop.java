package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color, 'b');
    }

    @Override
    public Direction findDirection(final Square current, final Square destination) {
        final int fileDifference = current.getFileDifference(destination);
        final int rankDifference = current.getRankDifference(destination);
        return PieceDirection.DIAGONAL.findDirection(fileDifference, rankDifference);
    }
}
