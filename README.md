# RoixCleanMvi

## IN PROGRESS

The feature is event channel based on kotlin coroutines 

```
IRoixChannel<Event> by RoixChannel()
```

RoixChannel is carcass of MVI chains 
```
    : IRoixChannel<Event> by RoixChannel() {
...
    init {

      go(multiAddUseCase)
                .with(
                    go(singleAddUseCase)
                )
                .to(initialState, mainReducer)
                .sub {
                    items.update(it.results)
                    Log.d("roix mvi", "sub" + Thread.currentThread().name)
                }
 ```
Usecase contains suspend fun go() wich executed in IO backgrond thread by default 

```
class SingleAddUseCase(
    val fuzzRepository: IFuzzRepository,
    val buzzRepository: IBuzzRepository,
    val stepRepository: IStepRepository
) :
    UseCase<Event, Update> {
    override suspend fun go(): (event: Event) -> Update? = {
        if (it is Event.SingleEvent) {
            Thread.sleep(300)
            val count = stepRepository.getNextStep()
            var ret = "$count "
            if (count % 3 == 0) {
                ret += fuzzRepository.getFuzz()
            }
            if (count % 5 == 0) {
                ret += buzzRepository.getBuzz()
            }
            Update(ret)

        } else null
    }
}
```

Reducer 

```
interface Reducer<S, U> {
    fun go(): (S, U) -> S
}
```
