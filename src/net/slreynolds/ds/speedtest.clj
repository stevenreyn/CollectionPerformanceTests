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

            
              
