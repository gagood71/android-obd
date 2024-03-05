# android-obd

<p>使用 https://github.com/pires/obd-java-api 以及 https://github.com/eltonvs/kotlin-obd-api 為核心，實現 OBD。</p>
<p>目前有分成兩種版本，分別是 Java 與 Kotlin，可依照個人喜好 Clone 不同版本。</p>

## Command

<p>主要用於實踐各種 OBD 指令，並取得車輛資訊（搭配 CommandListener 實現回調）。</p>

## EngineCommand

<p>集合引擎相關 Commands，以下為實際範例：</p>

```java
// 引擎附載 - 絕對值
new EngineCommand().getCommand(EngineCommand.ENGINE_ABSOLUTE_LOAD);

// 空氣流量
new EngineCommand().getCommand(EngineCommand.ENGINE_MASS_AIR_FLOW);

// 引擎轉速
new EngineCommand().getCommand(EngineCommand.ENGINE_RPM);

// 時速
new EngineCommand().getCommand(EngineCommand.ENGINE_SPEED);

// 踏板位置
new EngineCommand().getCommand(EngineCommand.ENGINE_THROTTLE_POSITION);
```

<p>如果是 Kotlin 版本，請看以下實際範例：</p>

```kotlin
// 引擎附載 - 絕對值
EngineCommand().getCommand(EngineCommand.ENGINE_ABSOLUTE_LOAD)

// 空氣流量
EngineCommand().getCommand(EngineCommand.ENGINE_MASS_AIR_FLOW)

// 引擎轉速
EngineCommand().getCommand(EngineCommand.ENGINE_RPM)

// 時速
EngineCommand().getCommand(EngineCommand.ENGINE_SPEED)

// 踏板位置
EngineCommand().getCommand(EngineCommand.ENGINE_THROTTLE_POSITION)
```

<p>指令類型包含以下：</p>

1. ENGINE_ABSOLUTE_LOAD（引擎附載 - 絕對值）
2. ENGINE_MASS_AIR_FLOW（空氣流量）
3. ENGINE_RPM（引擎轉速）
4. ENGINE_SPEED（時速）
5. ENGINE_THROTTLE_POSITION（踏板位置）

## OBDCommand

<p>為實驗性質，定義基礎 Command 流程，目前有使用的類別為：</p>

1. RPMCommand（引擎轉速）