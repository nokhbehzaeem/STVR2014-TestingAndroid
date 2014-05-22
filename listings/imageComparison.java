private boolean imagesEqual(){
    int columns = imageBefore.getWidth();
    int rows = imageBefore.getHeight();
    int diff = 0;
    for (int row = 0; row < rows; row++)
        for (int col = 0; col < columns; col++){
            int rgbBefore = imageBefore.getPixel(col, row);
            int rgbAfter = imageAfter.getPixel(col, row);
            if(Math.abs(rgbBefore - rgbAfter) > 0)
                diff++;
        }
    double diffRatio = 1.0 * diff / (columns * rows);
    if(diffRatio > 0.01)//allowed threshold
        return false;
    return true;
}