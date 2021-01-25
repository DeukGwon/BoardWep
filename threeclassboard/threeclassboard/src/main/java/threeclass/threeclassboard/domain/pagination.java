package threeclass.threeclassboard.domain;

public class pagination {

    private int pageSize = 10;

    private int blockSize = 10;

    private int page = 1;

    private int block = 1;

    private int totalListCnt;

    private int totalPageCnt;

    private int totalBloackCnt;

    private int startPage = 1;
    private int endPage = 1;

    private int startIndex = 0;
    private int preBlock;

    private int nextBlock;
}
