
public class Percolation {
	private class site{
		int[] link = new int[2];
		boolean stat = false;
		public site(int[] a) {
			link[0] = a[0];
			link[1] = a[1];
		}
	}
	//private static final Exception IllegalArgumentException =;
	private final site[][] grid;
	private int num = 0;
	//private int[][] size;
	// creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	if(n<=0)
    		throw new IllegalArgumentException("n is incorrect");
    	this.grid = new site[n+1][n+1];
    	for(int i = 1 ; i < n+1; i++) {
    		for(int j = 1; j< n+1; j++) {
    			int[] a = {i,j};
    			grid[i][j] = new site(a);
    		}
    	}
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	if(row<=0||col<=0||row>grid.length-1||col>grid.length-1)
    		throw new IllegalArgumentException("parameters is incorrect");
    	if(!isOpen(row,col)) {
    		grid[row][col].stat = true;
    		num++;
    		//size[row][col] = 1;
    		if(row-1>=1&&isOpen(row-1,col)) {
    			union(row,col,row-1,col);
    		}
    		if(row+1<grid.length&&isOpen(row+1,col)) {
    			union(row,col,row+1,col);
    		}
    		if(col-1>=1&&isOpen(row,col-1)) {
    			union(row,col,row,col-1);
    		}
    		if(col+1<grid.length&&isOpen(row,col+1)) {
    			union(row,col,row,col+1);
    		}
    		
    	}
    	
    }

    
//    public int root(int i) {
//		while(i!=array[i]) {
//			array[i] = array[array[i]];
//			i = array[i];
//		}
//		return i;
//	}
    private int[] root(int row,int col) {
    	if(row<=0||col<=0||row>grid.length-1||col>grid.length-1)
    		throw new IllegalArgumentException("parameters is incorrect");
    	if(!isOpen(row,col)) {
    		int[] a = {-1,-1};
    		return a;
    	}
    	int[] a = new int[2];
    	a[0] = row;
    	a[1] = col;
//    	!a.equals(grid[row][col].link)
    	//int[] b = grid[row][col].link;
    	while(a[0]!=grid[row][col].link[0]&&a[1]!=grid[row][col].link[1]) {
    		grid[row][col].link = grid[grid[row][col].link[0]][grid[row][col].link[1]].link;
    		a = grid[row][col].link;
    	}
    	
    	return a;
    }

//	public void union(int p, int q) {
//		int k1 = root(p);
//		int k2 = root(q);
//		//array[p] = k2;
//		if(k1==k2)
//			return;
//		else if(size[p]<size[q]) {
//			array[p] = q;
//			size[q] += size[p];
//		}else {
//			array[q] = p;
//			size[p] += size[q];
//		}
//	}
    private void union(int row, int col, int row2, int col2) {
		// TODO Auto-generated method stub
    	int[] k1 = root(row,col);
    	int[] k2 = root(row2,col2);
    	if(k1[0]==k2[0]&&k1[1]==k2[1])
    		return;
    	else if(k1[0]<k2[0]) {
    		grid[row2][col2].link = k1;
    	}else {
    		if(k1[0]==k2[0]&&k1[1]<k2[1]) {
    			grid[row2][col2].link = k1;
    		}else
    			grid[row][col].link = k2;
    	}
		
	}

	// is the site (row, col) open?
    public boolean isOpen(int row, int col){
    	if(row<=0||col<=0||row>grid.length-1||col>grid.length-1)
    		throw new IllegalArgumentException("parameters is incorrect");
		return grid[row][col].stat;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
    	if(row<=0||col<=0||row>grid.length-1||col>grid.length-1)
    		throw new IllegalArgumentException("parameters is incorrect");
		return root(row,col)[0]==1;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
    	
		return num;
    	
    }

    // does the system percolate?
    public boolean percolates(){
    	for(int i = 1 ; i < grid.length;i++) {
    		if(isFull(grid.length-1,i)) {
    			return true;
    		}
    	}
		return false;
    	
    }

    // test client (optional)
    public static void main(String[] args){
    	//Percolation grid = new Percolation(8);
//    	grid.open(1,6);
//    	grid.open(1,3);
//    	grid.open(1,4);
//    	grid.open(2,1);
//    	grid.open(2,2);
//    	grid.open(2,4);
//    	grid.open(2,5);
//    	grid.open(2,3);
//    	//grid.open(2,7);
//    	grid.open(3,2);
//    	grid.open(3,1);
//    	grid.open(3,5);
//    	grid.open(3,6);
//    	//grid.open(3,6);
//    	grid.open(4,4);
//    	grid.open(4,3);
//    	grid.open(4,5);
//    	grid.open(4,6);
//    	grid.open(4,7);
//    	grid.open(5,1);
//    	//grid.open(5,2);
//    	
//    	grid.open(5,7);
//    	grid.open(5,8);
//    	grid.open(6,2);
//    	grid.open(6,4);
//    	grid.open(6,5);
//    	grid.open(6,6);
//    	grid.open(7,2);
//    	grid.open(7,3);
//    	grid.open(7,8);
//    	grid.open(7,5);
//    	grid.open(7,6);
//    	//grid.open(7,7);
//    	//grid.open(8,4);
//    	grid.open(8,1);
//    	//grid.open(8,2);
//    	grid.open(8,3);
//    	grid.open(8,7);
//    	int[] a = grid.root(1,6);
//    	System.out.println(a[0]+"  "+a[1]);
//    	System.out.println(grid.percolates());


    	
    	
    }
	
}

