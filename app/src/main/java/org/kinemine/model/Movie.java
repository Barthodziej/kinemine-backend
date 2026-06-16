package org.kinemine.model;

import java.util.LinkedList;
import java.util.List;

public class Movie {
    public String title;
    public String imageUrl;
    public boolean watched;
    public List<String> tags = new LinkedList<String>();
}
