
        public static int[][] rotate(int[][] matrix, int flag){
            int m = matrix.length, n = matrix[0].length;
            int[][] newM = new int[n][m];
            if(matrix==null||matrix.length==0||matrix[0].length==0) return newM;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    newM[i][j] = flag == 0? matrix[m-1-j][i] : matrix[j][n-1-i];//1 逆时针，0顺时针
                }
            }
            return newM;
        }