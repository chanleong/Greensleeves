package questionbank;
/**
 * Generic Pair class
 * @author Raymond
 *
 * @param <L> 
 * @param <R>
 */
public class Pair<L, R> {
	private L left;
	private R right;

	  public Pair(L left, R right) {
	    this.left = left;
	    this.right = right;
	  }

	  public L getLeft() { return left; }
	  public R getRight() { return right; }
	  public void setLeft(L l){ this.left = l; }
	  public void setRight(R r){ this.right = r; }

	  @Override
	  public int hashCode() { return left.hashCode() ^ right.hashCode(); }

	  @Override
	  public boolean equals(Object o) {
	    if (o == null) return false;
	    if (!(o instanceof Pair)) return false;
	    Pair pairo = (Pair) o;
	    return this.left.equals(pairo.getLeft()) &&
	           this.right.equals(pairo.getRight());
	  
	  
	 }
	  
	 @Override
	 public String toString(){
		 String tmp = "[" + this.left + "," + this.right + "]" ;
		 return tmp;
	 }
}
