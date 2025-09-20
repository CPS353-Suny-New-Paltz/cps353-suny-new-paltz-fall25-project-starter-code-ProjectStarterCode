package shared.stuff;

/**
 * Generic wrapper for some data. Can hold any data source (List, Set, Stream,
 * String etc.).
 */
public class DataBatch<T> {

  private T dataSource;

  /**
   * @param dataSource
   *          The actual data source (e.g., List, Stream, Set, etc.)
   */
  public DataBatch(T dataSource) {
    this.dataSource = dataSource;
  }
  public DataBatch() {
    this.dataSource = null;
  }

  public T getDataSource() {
    return this.dataSource;
  }
  public void setDataSource(T data) {
    this.dataSource = data;
  }

}
