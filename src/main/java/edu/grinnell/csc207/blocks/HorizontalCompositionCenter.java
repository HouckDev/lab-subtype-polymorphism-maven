package edu.grinnell.csc207.blocks;

/**
 * The center-aligned horizontal composition of two blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class HorizontalCompositionCenter implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The left block.
   */
  AsciiBlock left;

  /**
   * The right block.
   */
  AsciiBlock right;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a Horizontal Composition.
   *
   * @param leftBlock
   *   The block on the left.
   *
   * @param rightBlock
   *   The block on the right.
   */
  public HorizontalCompositionCenter(AsciiBlock leftBlock, AsciiBlock rightBlock) {
    this.left = leftBlock;
    this.right = rightBlock;
  } // HorizontalCompositionCenter

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    AsciiBlock tempLeft = left;
    AsciiBlock tempRight = right;
    if (this.right.height() > this.left.height()) {
      for (int z = 1; z<this.right.height() - this.left.height();z++ ) {
        tempLeft = new VerticalCompositionLeft(new Line(" ".repeat(this.left.width())), left);
      }
    }
    if (this.left.height() > this.right.height()) {
      for (int z = 1; z<this.left.height() - this.right.height();z++ ) {
        tempRight = new VerticalCompositionLeft(new Line(" ".repeat(this.right.width())), right);
    
      }
    }
    if ((i < 0) || (i >= this.height())) {
      // Outside of normal bounds
      throw new Exception("Invalid row " + i);
    } else if ((i < tempLeft.height()) && (i < tempRight.height())) {
      // Inside both left and right bounds
      return tempLeft.row(i) + tempRight.row(i);
    } else if (i < tempRight.height()) {
      // Inside right bounds, outside left bounds
      return " ".repeat(tempLeft.width()) + tempRight.row(i);
    } else if (i < tempLeft.height()) {
      // Inside right bounds, outside left bounds
      return tempLeft.row(i) + " ".repeat(tempRight.width());
    } else {
      // Inside left bounds, outside right bounds
      return tempLeft.row(i);
    } // if/else
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return Math.max(this.left.height(), this.right.height());
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.left.width() + this.right.width();
  } // width()

} // class HorizontalCompositionCenter
