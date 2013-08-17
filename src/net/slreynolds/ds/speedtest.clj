(ns net.slreynolds.ds.speedtest)

; Warmup the jit compiler etc in the JVM
; before doing any measurements
(defn warmup[setup dowork]
  (let [computation 7]
       (dotimes [_ N]
         (let [o (setup)
               res (dowork o)
               newComp (+ computation (. res getIntParam))]
           newComp))))
           

; Measure space (bytes) consumed by side effects
; of dowork
(defn measure-space[setup dowork]
      
        (let [computation 7
              o (setup)]
          (System/gc)
          (System/gc)
          (System/gc)
          (let [runt (Runtime/getRuntime)
                before (. runt totalMemory)
                res (dowork o)
                (System/gc)
                (System/gc)
                (System/gc)
                after (. runt totalMemory)
                otherComp (+ computation (. res getIntParam))]
            (Math/max 0 (- after before)))))

   
; Do dowork N times and record the time taken.
; Return list of times
(defn timeit[setup dowork N]
  (loop [ctr (- N 1)
         times (vector)
         computation 7]
    (let [o (setup)
          before (System/nanotime)
          res (dowork o)
          after (System/nanotime)
          (conj times (- after before))]
      (if (zero? ctr)
        times
        (recur ((dec ctr) times (+ computation (. res getIntParam))))))))
      
       
; Run tests: warmup, measure-space, and then timeit
; Print results
(defn run [setup dowork title]
  (let [res (warmup setup dowork 20)]
    (print "res " res)
    (let [space (measure-space setup dowork)
          times (timeit setup dowork 5)]
      (print title " uses " space " bytes ")
      (print "Times (seconds)")
      (print times))))
        
        

