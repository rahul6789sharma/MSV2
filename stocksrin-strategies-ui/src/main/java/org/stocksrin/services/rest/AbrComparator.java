package org.stocksrin.services.rest;


import java.util.Comparator;
import org.stocksrin.v2.common.model.future.EntityABR;

class AbrComparator implements Comparator<EntityABR> {
  public int compare(EntityABR o1, EntityABR o2) {
    if (o1 != null && o2 != null && 
      o1.getAbrPercenatge() != null && o2.getAbrPercenatge() != null)
      return o1.getAbrPercenatge().compareTo(o2.getAbrPercenatge()); 
    return 0;
  }
}