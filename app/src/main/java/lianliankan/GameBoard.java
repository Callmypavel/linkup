package lianliankan;

import java.util.ArrayList;
import java.util.List;

import lianliankan.ImageUtil;


public class GameBoard {
	public List<Piece> createPieces(GameConf config,Piece[][] pieces){
		List<Piece> notNullPieces = new ArrayList<Piece>();
		for(int i=0;i<pieces.length-1;i++)
			for(int j=0;j<pieces[i].length-1;j++){
				Piece piece = new Piece(i,j);
				notNullPieces.add(piece);
			}return notNullPieces;
		
	}
	public Piece[][] create(GameConf config){
		Piece[][] pieces = new Piece[config.getXsize()][config.getYsize()];
		List<Piece> notNullPieces = createPieces(config,pieces);
		List<PieceImage> PlayImages=ImageUtil.getPlayImages(config.getPiecesNumber(), config.getContext());
		for(int i=0;i<notNullPieces.size();i++){
				Piece piece = notNullPieces.get(i);
				piece.setPieceImage(PlayImages.get(i));
				int imageWidth = PlayImages.get(0).getBitmap().getWidth();
				int imageHeight = PlayImages.get(0).getBitmap().getHeight();
				piece.setBeginx(piece.getindexX()*imageWidth+config.getBeginX());
				piece.setBeginy(piece.getindexY()*imageHeight+config.getBeginY());
				pieces[piece.getindexX()][piece.getindexY()]=piece;
		}
		return pieces;
	}
}
