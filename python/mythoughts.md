
#1
Somebody asked me why do I prefer Impertive(Dataset/Dataframe) over Declarative query(Sql/SparkSql) in distributed system?
Because as of now, atleast in case of distributed system, underlying Query Optimizer is not very advanced, 
so I have seen several cases when a non-preetier scala/python code performed way better/faster than small and easy-to-read sql 
syntax AND in distributed system, speed and optimization matter a lot, so atleast for now I've a reason :-) 



#2
Why is it important to know #datastructure for #bigdata ?
All major distributed #nosql databases are based on SSTables, 
which makes compaction important & that makes mergesort super important which in general known as
LSM-tree (merging & compaction).
It doesn't mean B-Tree is going anywhere, the enormous development and optimizations around B-tree for last many years 
has made the datastructure - King (databases) !!!

Basically, LSM-trees are faster for writes and but slower to Read not because of SSTable datastructure but
more around the distributed principles like validating different data structures, compaction , slots, storage and others.

If you notice carefully, I have named few datastructures above, if I try to go in more details, I may end up writing a big big blog,
so yeah Datastructures are Very Important, even for #bigdata !!!


#3
Why Colum-Oriented Storage (#parquet, Apache Arrow)
Column Oriented Storage like #parquet is an excellent choice for OLAP system (read-only) not only for accessing data smartly but for compression as well.
Optimize further, consider Sorted Order in Column Storage which makes compression based on initial columns, a niche trick !!!

But the biggest problem while scanning rows is - the Bandwidth of getting data from Disk to memory & Column Storage are again a great system for efficient use of CPU cycles.
Like a chunk of compressed Columnar data can fit comfortably  in CPU's L1 cache & iterate through it in a tight loop without any function calls, So much efficient CPU Cycles!!! 
This concept is already widely used and known as Vectorized Processing #apachearrow


#4
