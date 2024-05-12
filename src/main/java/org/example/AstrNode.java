package org.example;

public class AstrNode extends Node implements Comparable<AstrNode>{

    int h;
    int g;
    int f;

    public AstrNode(int[][] board, int depth, StringBuilder history, int h, int g) {
        super(board, depth, history);
        this.h = h;
        this.g = g;
        this.f = h+g;

    }

    @Override
    public int compareTo(AstrNode o) {
        return Integer.compare(this.f, o.f);
    }
}
