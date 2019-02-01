package com.xgit.bj.core;

public class Ref<T>
{
  T value;
  
  public Ref(T value)
  {
    this.value = value;
  }
  
  public T get()
  {
    return (T)this.value;
  }
  
  public void set(T value)
  {
    this.value = value;
  }
  
  public String toString()
  {
    return this.value.toString();
  }
}
